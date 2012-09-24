<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Informações do Usuário</title>
            <link rel="stylesheet" type="text/css" href="/SistemaRodoviario-war/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Cadastro do Usuário</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Identificador:"/>
                <h:outputText value="#{usuario.usuario.idUsuario}" title="IdUsuario" />
                <h:outputText value="Você Mora em:"/>
                <h:outputText value="#{usuario.usuario.cidadeUsuario}" title="CidadeUsuario" />
                <h:outputText value="Bairo:"/>
                <h:outputText value="#{usuario.usuario.bairoUsuario}" title="BairoUsuario" />
                <h:outputText value="Endereco:"/>
                <h:outputText value="#{usuario.usuario.enderecoUsuario}" title="EnderecoUsuario" />
                <h:outputText value="UF:"/>
                <h:outputText value="#{usuario.usuario.ufUsuario}" title="UfUsuario" />
                <h:outputText value="Senha:"/>
                <h:outputText value="#{usuario.usuario.senhaUsuario}" title="SenhaUsuario" />
                <h:outputText value="Nome:"/>
                <h:outputText value="#{usuario.usuario.nomeUsuario}" title="NomeUsuario" />
                <h:outputText value="Email:"/>
                <h:outputText value="#{usuario.usuario.emailUsuario}" title="EmailUsuario" />
                <h:outputText value="RG:"/>
                <h:outputText value="#{usuario.usuario.rgUsuario}" title="RgUsuario" />
                <h:outputText value="CPF:"/>
                <h:outputText value="#{usuario.usuario.cpfUsuario}" title="CpfUsuario" />


            </h:panelGrid>
            <br />
            <h:commandLink action="#{usuario.remove}" value="Cancelar cadastro">
                <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['br.com.sistema.rodoviario.controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][usuario.usuario][usuario.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{usuario.editSetup}" value="Editar Informações">
                <f:param name="jsfcrud.currentUsuario" value="#{jsfcrud_class['br.com.sistema.rodoviario.controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][usuario.usuario][usuario.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink value="Home" action="/welcomeJSF" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
