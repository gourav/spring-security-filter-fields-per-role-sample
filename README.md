# spring-security-filter-fields-per-role-sample

This repository demonstrates how you can filter fields as per user's role in `Authentication`.

You configure role to json view mapping via custom `AuthorityToJsonViewMapping` annotation, this configuration is in turn read by a subclass of `AbstractMappingJacksonResponseBodyAdvice` to select appropriate serialization view.
