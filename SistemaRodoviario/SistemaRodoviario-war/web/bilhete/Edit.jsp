<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Bilhete</title>
            <link rel="stylesheet" type="text/css" href="/SistemaRodoviario-war/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Editar Bilhete</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Numero do bilhete:"/>
                <h:outputText value="#{bilhete.bilhete.idBilhete}" title="IdBilhete" />
                <h:outputText value="Valor do Bilhete:"/>
                <h:inputText id="valorBilhete" value="#{bilhete.bilhete.valorBilhete}" title="ValorBilhete" required="true" requiredMessage="The valorBilhete field is required." />
                <h:outputText value="Nome do Passageiro:"/>
                <h:inputText id="nomePassageiro" value="#{bilhete.bilhete.nomePassageiro}" title="NomePassageiro" required="true" requiredMessage="The nomePassageiro field is required." />
                <h:outputText value="Origem:"/>
                <h:inputText id="origem" value="#{bilhete.bilhete.origem}" title="Origem" required="true" requiredMessage="The origem field is required." />
                <h:outputText value="Destino:"/>
                <h:inputText id="destino" value="#{bilhete.bilhete.destino}" title="Destino" required="true" requiredMessage="The destino field is required." />
                <h:outputText value="Linha:"/>
                <h:inputText id="linha" value="#{bilhete.bilhete.linha}" title="Linha" required="true" requiredMessage="The linha field is required." />
                <h:outputText value="Data Ida:"/>
                <h:inputText id="dataIda" value="#{bilhete.bilhete.dataIda}" title="DataIda" >
                    <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                </h:inputText>
                <h:outputText value="Data Volta:"/>
                <h:inputText id="dataVolta" value="#{bilhete.bilhete.dataVolta}" title="DataVolta" >
                    <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                </h:inputText>
                  <h:outputText value="Assento:"/>
                <h:selectOneMenu id="assento" value="#{assento.assento.numeroAssento}" title="Assento" required="true" requiredMessage="The assento field is required." >
                    <f:selectItems value="#{assento.assentoItemsAvailableSelectOne}"/>
                </h:selectOneMenu>
                <h:outputText value="Local:"/>
                <h:inputText id="localAssento" value="#{bilhete.bilhete.localAssento}" title="LocalAssento" required="true" requiredMessage="The localAssento field is required." />
                <h:outputText value="Veiculo:"/>
                <h:selectOneMenu id="veiculo" value="#{bilhete.bilhete.veiculo}" title="Veiculo" required="true" requiredMessage="The veiculo field is required." >
                    <f:selectItems value="#{veiculo.veiculoItemsAvailableSelectOne}"/>
                </h:selectOneMenu>

            </h:panelGrid>
            <br />
            <h:commandLink action="#{bilhete.edit}" value="Salvar">
                <f:param name="jsfcrud.currentBilhete" value="#{jsfcrud_class['br.com.sistema.rodoviario.controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][bilhete.bilhete][bilhete.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{bilhete.detailSetup}" value="Visualizar" immediate="true">
                <f:param name="jsfcrud.currentBilhete" value="#{jsfcrud_class['br.com.sistema.rodoviario.controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][bilhete.bilhete][bilhete.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <br />
            <h:commandLink value="Voltar" action="/welcomeJSF" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
