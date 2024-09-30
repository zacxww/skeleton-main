# skeleton
skeleton default project

</subsystem>
<subsystem xmlns="urn:jboss:domain:bean-validation:1.0"/>
<subsystem xmlns="urn:jboss:domain:core-management:1.0"/>
<subsystem xmlns="urn:jboss:domain:datasources:7.1">
<datasources>
<datasource jndi-name="java:jboss/datasources/PostgresDS" pool-name="PostgresDS" enabled="true" use-java-context="true">
<connection-url>jdbc:postgresql://localhost:5432/TesteDesenvolvimento</connection-url>
<driver>postgresql</driver>
<security user-name="postgres" password="batatasalsa54323"/>
</datasource>
<drivers>
<driver name="postgresql" module="org.postgresql">
<xa-datasource-class>org.postgresql.xa.PGXADataSource</xa-datasource-class>
</driver>
</drivers>
</datasources>
</subsystem>