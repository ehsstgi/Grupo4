<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Bilhete Detail</title>
            <link rel="stylesheet" type="text/css" href="/SistemaRodoviario-war/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Detalhes da Viagem</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Numero do Bilhete:"/>
                <h:outputText value="#{bilhete.bilhete.idBilhete}" title="IdBilhete" />
                <h:outputText value="Valor (em R$):"/>
                <h:outputText value="#{bilhete.bilhete.valorBilhete}" title="ValorBilhete" />
                <h:outputText value="Nome do Passageiro:"/>
                <h:outputText value="#{bilhete.bilhete.nomePassageiro}" title="NomePassageiro" />
                <h:outputText value="Numero do Assento:"/>
                <h:outputText value="#{bilhete.bilhete.numeroAssento}" title="NumeroAssento" />
                <h:outputText value="Local:"/>
                <h:outputText value="#{bilhete.bilhete.localAssento}" title="LocalAssento" />
                <h:outputText value="Origem:"/>
                <h:outputText value="#{bilhete.bilhete.origem}" title="Origem" />
                <h:outputText value="Destino:"/>
                <h:outputText value="#{bilhete.bilhete.destino}" title="Destino" />
                <h:outputText value="Linha:"/>
                <h:outputText value="#{bilhete.bilhete.linha}" title="Linha" />
                <h:outputText value="Data Ida:"/>
                <h:outputText value="#{bilhete.bilhete.dataIda}" title="DataIda" >
                    <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                </h:outputText>
                <h:outputText value="Data Volta:"/>
                <h:outputText value="#{bilhete.bilhete.dataVolta}" title="DataVolta" >
                    <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                </h:outputText>
                <h:outputText value="Veiculo:"/>
                <h:panelGroup>
                    <h:outputText value="#{bilhete.bilhete.veiculo}"/>
                    <h:panelGroup rendered="#{bilhete.bilhete.veiculo != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{veiculo.detailSetup}">
                            <f:param name="jsfcrud.currentBilhete" value="#{jsfcrud_class['br.com.sistema.rodoviario.controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][bilhete.bilhete][bilhete.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentVeiculo" value="#{jsfcrud_class['br.com.sistema.rodoviario.controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][bilhete.bilhete.veiculo][veiculo.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="bilhete"/>
                            <f:param name="jsfcrud.relatedControllerType" value="br.com.sistema.rodoviario.controller.BilheteController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{veiculo.editSetup}">
                            <f:param name="jsfcrud.currentBilhete" value="#{jsfcrud_class['br.com.sistema.rodoviario.controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][bilhete.bilhete][bilhete.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentVeiculo" value="#{jsfcrud_class['br.com.sistema.rodoviario.controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][bilhete.bilhete.veiculo][veiculo.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="bilhete"/>
                            <f:param name="jsfcrud.relatedControllerType" value="br.com.sistema.rodoviario.controller.BilheteController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{veiculo.destroy}">
                            <f:param name="jsfcrud.currentBilhete" value="#{jsfcrud_class['br.com.sistema.rodoviario.controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][bilhete.bilhete][bilhete.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentVeiculo" value="#{jsfcrud_class['br.com.sistema.rodoviario.controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][bilhete.bilhete.veiculo][veiculo.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="bilhete"/>
                            <f:param name="jsfcrud.relatedControllerType" value="br.com.sistema.rodoviario.controller.BilheteController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>


            </h:panelGrid>
            <br />
            <h:commandLink action="#{bilhete.remove}" value="Cancelar compra">
                <f:param name="jsfcrud.currentBilhete" value="#{jsfcrud_class['br.com.sistema.rodoviario.controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][bilhete.bilhete][bilhete.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{bilhete.editSetup}" value="Editar Detalhes">
                <f:param name="jsfcrud.currentBilhete" value="#{jsfcrud_class['br.com.sistema.rodoviario.controller.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][bilhete.bilhete][bilhete.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink value="Salvar" action="#{bilhete.create}" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
