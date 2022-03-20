package blog.explained.so;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RoleBasedJsonSerializationControllerAdvice extends AbstractMappingJacksonResponseBodyAdvice {

  @Override
  protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType, MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {

    Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();

    Class<?> bodyClass = bodyContainer.getValue().getClass();
    AuthorityToJsonViewMappings mappings = bodyClass.getAnnotation(AuthorityToJsonViewMappings.class);

    if(mappings == null) {
      return;
    }

    Map<String, Class<?>> authorityToViewMap = new HashMap<>();

    for (AuthorityToJsonViewMapping authorityToJsonViewMapping : mappings.value()) {
      authorityToViewMap.put(authorityToJsonViewMapping.authority(), authorityToJsonViewMapping.view());
    }

    for (GrantedAuthority authority : currentAuthentication.getAuthorities()) {

      if(authorityToViewMap.get(authority.getAuthority()) != null) {
        bodyContainer.setSerializationView(authorityToViewMap.get(authority.getAuthority()));
        return;

      }
    }

  }
}
