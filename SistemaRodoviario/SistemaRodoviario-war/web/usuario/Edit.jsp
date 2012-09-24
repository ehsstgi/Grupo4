<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Alterar dados</title>
            <link rel="stylesheet" type="text/css" href="/SistemaRodoviario-war/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Alterar dados</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Identificador:"/>
                <h:outputText value="#{usuario.usuario.idUsuario}" title="IdUsuario" />
                <h:outputText value="Cidade:"/>
                <h:inputText id="cidadeUsuario" value="#{usuario.usuario.cidadeUsuario}" title="CidadeUsuario" required="true" requiredMessage="The cidadeUsuario field is required." />
                <h:outputText value="Bairro:"/>
                <h:inputText id="bairoUsuario" value="#{usuario.usuario.bairoUsuario}" title="BairoUsuario" required="true" requiredMessage="The bairoUsuario field is required." />
                <h:outputText value="Endereco:"/>
                <h:inputText id="enderecoUsuario" value="#{usuario.usuario.enderecoUsuario}" title="EnderecoUsuario" required="true" requiredMessage="The enderecoUsuario field is required." />
                <h:outputText value="UF:"/>
                <h:inputText id="ufUsuario" value="#{usuario.usuario.ufUsuario}" title="UfUsuario" required="true" requiredMessage="The ufUsuario field is required." />
                <h:outputText value="Senha:"/>
                <h:inputText id="senhaUsuario" value="#{usuario.usuario.senhaUsuario}" title="SenhaUsuario" required="true" requiredMessage="The senhaUsuario field is required." />
                <h:outputText value="Nome:"/>
                <h:inputText id="nomeUsuario" value="#{usuario.usuario.nomeUsuario}" title="NomeUsuario" required="true" requiredMessage="The nomeUsuario field is required." />
                <h:outputText value="Email:"/>
                <h:inputText id="emailUsuario" value="#{usuario.usuario.emailUsuario}" title="EmailUsuario" required="true" requiredMessage="The emailUsuario field is required." />
                <h:outputText value="RG:"/>
                <h:inputText id="rgUsuario" value="#{usuario.usuario.rgUsuario}" title="RgUsuario" />
                <h:outputText value="CPF:"/>
                <h:inputText id="cpfUsuario" value="#{usuario.usuario.cpfUsuario}" title="CpfUsuario" required="true" requiredMessage="The cpfUsuario field is required." />

            </h:panelGrid>
            <br />
            <h:commandLink action="#{usuario.edit}" value="Salvar">
                <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['br.com.sistema.rodoviario.controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][usuario.usuario][usuario.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{usuario.detailSetup}" value="Exibir detalhes" immediate="true">
                <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['br.com.sistema.rodoviario.controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][usuario.usuario][usuario.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <br />
            <h:commandLink value="Home" action="/welcomeJSF" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
