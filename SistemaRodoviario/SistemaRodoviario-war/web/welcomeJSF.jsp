<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Sistema Rodoviario</title>
            <link rel="stylesheet" type="text/css" href="/SistemaRodoviario-war/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Sistema Rodoviario</h1>
        <h:form>
            <h:inputHidden id="validateCreateField" validator="#{bilhete.validateCreate}" value="value"/>
            <h:panelGrid columns="2">
                <h:outputLabel value="Cidade Origem: " for="nomeCidade" />
                <h:selectOneMenu id="plataformaCollection" value="#{plataforma.plataforma.nomePlataforma}" title="Plataforma" required="true" requiredMessage="The Plataforma field is required." >
                    <f:selectItems value="#{plataforma.plataformaItemsAvailableSelectMany}"/>
                </h:selectOneMenu>
                <h:outputLabel value="Cidade Destino: " for="nomeCidade" />
                <h:selectOneMenu id="plataformaCollectionVolta" value="#{plataforma.plataforma.nomePlataforma}" title="Plataforma" required="false">
                    <f:selectItems value="#{plataforma.plataformaItemsAvailableSelectMany}"/>
                </h:selectOneMenu>
                <h:outputText value="Data Ida:"/>
                <h:inputText id="dataIda" value="#{bilhete.bilhete.dataIda}" title="DataIda" >
                    <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                </h:inputText>
                <h:outputText value="Data Volta:"/>
                <h:inputText id="dataVolta" value="#{bilhete.bilhete.dataVolta}" title="DataVolta" >
                    <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                </h:inputText>
                
            </h:panelGrid>
            <br />
            <h:commandLink action="#{bilhete.create}" value="Verificar Disponibilidade"/>
            <br />
            <h:commandLink value="Confirmar compra" action="/bilhete/Edit" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Cancelar" action="/welcomeJSF" immediate="true" />
            <br />
            <br />
            <h:commandLink value="Ainda não é cadastrado" action="/usuario/New" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
