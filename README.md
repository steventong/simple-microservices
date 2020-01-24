# 一个简单的微服务
包含鉴权服务 auth-service，网关服务 gateway-service，业务层 api-service

所有服务基于 spring boot 2.2.0.RELEASE & spring cloud Hoxton.M3 进行开发。

api-service 是业务服务，提供业务接口，没有 token 验证；通过 gateway-service 可以访问 api-service 的业务接口，并在 gateway 层面实现了统一的用户认；auth-service 提供用户认证和用户鉴权能力。

### auth-service
- 使用 spring cloud oauth2，实现一个简单的基本的 oauth2 provider
- 使用 jwt token，使用自定义 JwtTokenStore
- 提供 `/.well-known/jwks.json` 端点

### gateway-service
- 使用 spring cloud gateway 实现简单路由
- 作为 oauth2 resource server 接入 auth-service

### api-service
- 提供简单的 Restful API，通过 gateway-service 调用


## 运行
依次运行 auth-service，gateway-service，api-service

获取 access token
```shell script
curl -X POST \
  http://localhost:8081/oauth/token \
  -d grant_type=password \
  -d client_id=test-client \
  -d client_secret=test-secret \
  -d username=user-username \
  -d password=user-password
```

不带 token 访问接口，返回 401 Unauthorized
```shell script
curl -X GET http://localhost:8082/api/hello -sI
```

带 token 访问接口
```shell script
curl -X GET \
  http://localhost:8082/api/hello \
  -H 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NzI0ODY3ODksInVzZXJfbmFtZSI6InVzZXItdXNlcm5hbWUiLCJhdXRob3JpdGllcyI6WyJVU0VSIl0sImp0aSI6IjA3MDZmOWUyLWRlMWYtNDg4ZS1hNTJhLTUzMjdiMjU4ZTI4ZiIsImNsaWVudF9pZCI6InRlc3QtY2xpZW50Iiwic2NvcGUiOlsiZGVmYXVsdC1zY29wZSJdfQ.IqSmO7rH_lFpQ8__ZP1rwSDh5S6kw3EPaT3gs-byW_usN5iM0l0ohEV1OCb1jOUjcMoxnqUM-C3ZEND_tVdGlpscNVXT1avkVGw8Rc2Y49_Ee0OYgLwg97LOFdoHgCIEWKqronk5EdZJES1Zv95MbGSO1o6U_mo8My1-znxNgUwYpGt1sNUsaoCRzIKEQR--67IWe3EMmxo1D0IcPPi0fgKbVD2LR7mOQTqvQMHEF4LplS67mbfUWLFJg9Q4gyH0l3ndgGkCIcofPop9-GlwNCgp52RiGQlp8MCXydHbybr-_g-nXrstxSHQ-1iC0ihIMSueDhEuN7gLe5OELBW1oA'
```

## 实现思路参考文章
[Spring Cloud Gateway 基于 OAuth2.0 的身份认证](https://mp.weixin.qq.com/s/4v_wwX0SS7jvOwtO8uiDAw)
