/*
 * Copyright (c) 2024 Ernesto López
 *
 * Todos los derechos reservados.
 *
 * Este programa es software libre; puedes redistribuirlo y/o modificarlo
 * bajo los términos de la Licencia Pública General GNU tal como fue publicada por
 * la Free Software Foundation; ya sea la versión 3 de la Licencia, o
 * (a tu elección) cualquier versión posterior.
 *
 * Este programa se distribuye con la esperanza de que sea útil,
 * pero SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita de
 * COMERCIABILIDAD o IDONEIDAD PARA UN PROPÓSITO PARTICULAR. Consulta los
 * detalles de la Licencia Pública General GNU para obtener más detalles.
 */
 package com.elopez.scrum.platform.config;
 import java.util.List;
 
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 
 import io.swagger.v3.oas.models.OpenAPI;
 import io.swagger.v3.oas.models.info.Contact;
 import io.swagger.v3.oas.models.info.Info;
 import io.swagger.v3.oas.models.info.License;
 import io.swagger.v3.oas.models.servers.Server;
 
 @Configuration
 public class OpenAPIConfig {
 
   @Value("${scrumplatform.openapi.dev-url}")
   private String devUrl;
 
   @Value("${scrumplatform.openapi.prod-url}")
   private String prodUrl;
 
   @Bean
   public OpenAPI myOpenAPI() {
     Server devServer = new Server();
     devServer.setUrl(devUrl);
     devServer.setDescription("Server URL in Development environment");
 
     Server prodServer = new Server();
     prodServer.setUrl(prodUrl);
     prodServer.setDescription("Server URL in Production environment");
 
     Contact contact = new Contact();
     contact.setEmail("ernestodaniellopez504@gmail.com");
     contact.setName("Ernesto López");
     contact.setUrl("https://github.com/Ernestolop");
 
     License gplLicense = new License().name("GNU GPLv3").url("https://www.gnu.org/licenses/gpl-3.0.en.html");
 
     Info info = new Info()
         .title("Scrum Platform API")
         .version("0.0")
         .contact(contact)
         .description("This API helps to implement SCRUM framework correctly in teams.")
         .termsOfService("https://github.com/Ernestolop/scrum-platform?tab=GPL-3.0-1-ov-file")
         .license(gplLicense);
 
     return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
   }
 }
 