package site.yanglong.cloud.oauth2.server.service.impl;

import site.yanglong.cloud.oauth2.server.mapper.OauthClientDetailsMapper;
import site.yanglong.cloud.oauth2.server.model.OauthClientDetails;
import site.yanglong.cloud.oauth2.server.service.OauthClientDetailsService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * OAuth2客户端信息表 服务实现类
 * </p>
 *
 * @author Dr.YangLong
 * @since 2018-08-27
 */
@Service("oauthClientDetailsServiceImpl")
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements OauthClientDetailsService, ClientDetailsService, ClientRegistrationService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        //构造BaseClientDetails返回
        BaseClientDetails clientDetails = null;
        QueryWrapper<OauthClientDetails> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(OauthClientDetails::getClientId, clientId);
        OauthClientDetails client = this.selectOne(wrapper);
        if (null != client && Objects.equals(client.getEnabled(), "0")) {
            clientDetails = transferOAuthDetails(client);
        }
        return clientDetails;
    }

    /**
     * 将OAuth2ClientDetails中的属性转换到ClientDetails中
     *
     * @param client OauthClientDetails
     * @return BaseClientDetails
     */
    private BaseClientDetails transferOAuthDetails(OauthClientDetails client) {
        BaseClientDetails clientDetails = new BaseClientDetails(client.getClientId(), client.getResourceIds(), client.getScope(), client.getAuthorizedGrantTypes(),
                client.getAuthorities(), client.getWebServerRedirectUri());
        clientDetails.setAccessTokenValiditySeconds(client.getAccessTokenValidity());
        clientDetails.setRefreshTokenValiditySeconds(client.getRefreshTokenValidity());
        clientDetails.setClientSecret(client.getClientSecret());
        String mapStr = client.getAdditionalInformation();
        if (!StringUtils.isEmpty(mapStr)) {
            JSONObject json = JSONObject.parseObject(mapStr);
            Map<String, Object> info = new HashMap<>();
            json.forEach(info::put);
            clientDetails.setAdditionalInformation(info);
        }
        clientDetails.setAutoApproveScopes(StringUtils.commaDelimitedListToSet(client.getAutoapprove()));
        return clientDetails;
    }

    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
        OauthClientDetails details = new OauthClientDetails();
        transferClientDetails(details, clientDetails);
        details.setClientSecret(passwordEncoder.encode(clientDetails.getClientSecret()));
        this.insert(details);
    }

    /**
     * 获取自动授权的scope
     *
     * @param clientDetails clientDetails
     * @return 逗号分隔字符串scope
     */
    private String getAutoApproveScopes(ClientDetails clientDetails) {
        if (clientDetails.isAutoApprove("true")) {
            return "true"; // all scopes autoapproved
        }
        Set<String> scopes = new HashSet<String>();
        for (String scope : clientDetails.getScope()) {
            if (clientDetails.isAutoApprove(scope)) {
                scopes.add(scope);
            }
        }
        return StringUtils.collectionToCommaDelimitedString(scopes);
    }


    /**
     * 将ClientDetails中的属性转换到OauthClientDetails中
     *
     * @param details       自定义clientDetails
     * @param clientDetails clientDetails
     */
    private void transferClientDetails(OauthClientDetails details, ClientDetails clientDetails) {
        if (null == details || null == clientDetails) {
            return;
        }
        details.setResourceIds(clientDetails.getResourceIds() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails
                .getResourceIds()) : null);
        details.setScope(clientDetails.getScope() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails
                .getScope()) : null);
        details.setAuthorizedGrantTypes(clientDetails.getAuthorizedGrantTypes() != null ? StringUtils
                .collectionToCommaDelimitedString(clientDetails.getAuthorizedGrantTypes()) : null);
        details.setWebServerRedirectUri(clientDetails.getRegisteredRedirectUri() != null ? StringUtils
                .collectionToCommaDelimitedString(clientDetails.getRegisteredRedirectUri()) : null);
        details.setAuthorities(clientDetails.getAuthorities() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails
                .getAuthorities()) : null);
        details.setAccessTokenValidity(clientDetails.getAccessTokenValiditySeconds());
        details.setRefreshTokenValidity(clientDetails.getRefreshTokenValiditySeconds());
        details.setAdditionalInformation(clientDetails.getAdditionalInformation() == null ? null : JSONObject.toJSONString(clientDetails.getAdditionalInformation()));
        details.setAutoapprove(getAutoApproveScopes(clientDetails));
        details.setClientId(clientDetails.getClientId());
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        //不更新密码
        UpdateWrapper<OauthClientDetails> wrapper = new UpdateWrapper<>();
        wrapper.lambda().eq(OauthClientDetails::getClientId, clientDetails.getClientId());
        OauthClientDetails details = new OauthClientDetails();
        transferClientDetails(details, clientDetails);
        this.update(details, wrapper);
    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        //更新密码
        UpdateWrapper<OauthClientDetails> wrapper = new UpdateWrapper<>();
        wrapper.lambda().eq(OauthClientDetails::getClientId, clientId);
        wrapper.set("clientSecret", passwordEncoder.encode(secret));
        this.update(new OauthClientDetails(), wrapper);

    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        //删除
        UpdateWrapper<OauthClientDetails> wrapper = new UpdateWrapper<>();
        wrapper.lambda().eq(OauthClientDetails::getClientId, clientId);
        OauthClientDetails details = new OauthClientDetails();
        details.setEnabled("1");
        this.update(details, wrapper);
    }

    @Override
    public List<ClientDetails> listClientDetails() {
        List<OauthClientDetails> details = this.selectList(new QueryWrapper<OauthClientDetails>().eq("enabled", "0"));
        List<ClientDetails> list = details.stream().map(this::transferOAuthDetails).collect(Collectors.toList());
        return list;
    }
}
