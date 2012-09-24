<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Cadastro</title>
            <link rel="stylesheet" type="text/css" href="/SistemaRodoviario-war/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Cadastro de novo usuário</h1>
        <h:form>
            <h:inputHidden id="validateCreateField" validator="#{usuario.validateCreate}" value="value"/>
            <h:panelGrid columns="2">
                <h:outputText value="Identificador do Usuario:"/>
                <h:inputText id="idUsuario" value="#{usuario.usuario.idUsuario}" title="Identificador do Usuario" required="true" requiredMessage="The idUsuario field is required." />
                <h:outputText value="Cidade:"/>
                <h:inputText id="cidadeUsuario" value="#{usuario.usuario.cidadeUsuario}" title="CidadeUsuario" required="true" requiredMessage="The cidadeUsuario field is required." />
                <h:outputText value="Bairo:"/>
                <h:inputText id="bairoUsuario" value="#{usuario.usuario.bairoUsuario}" title="BairoUsuario" required="true" requiredMessage="The bairoUsuario field is required." />
                <h:outputText value="Endereço:"/>
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
            <h:commandLink action="#{usuario.create}" value="Ok, Cadastrar!"/>
            <br />
            <br />
            <h:commandLink value="Página Inicial" action="/welcomeJSF" immediate="true" />
            <br />
            <br />
            <h:commandLink value="Visualizar Dados" action="/usuario/Detail" immediate="true" />
        </h:form>
        </body>
    </html>
</f:view>
