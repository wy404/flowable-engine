/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.flowable.engine.test.bpmn.event;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.ExtensionElement;
import org.flowable.bpmn.model.Message;
import org.flowable.bpmn.model.MessageEventDefinition;
import org.flowable.engine.impl.bpmn.parser.BpmnParse;
import org.flowable.engine.impl.bpmn.parser.handler.MessageEventDefinitionParseHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MessageEventDefinitionWithExtensionElementsTest {

    @Test
    public void testParseMessagedDefinitionWithExtension() {
        BpmnParse bpmnParseMock = Mockito.mock(BpmnParse.class);
        MessageEventDefinition messageEventDefinitionMock = Mockito.mock(MessageEventDefinition.class);
        BpmnModel bpmnModelMock = Mockito.mock(BpmnModel.class);
        Message messageMock = Mockito.mock(Message.class);

        @SuppressWarnings("unchecked")
        Map<String, List<ExtensionElement>> messageExtensionElementMapMock = Mockito.mock(Map.class);
        Collection<List<ExtensionElement>> messageExtensionElementListCollectionMock = Mockito.mock(List.class);
        Map<String, List<ExtensionElement>> messageDefinitionExtensionElementMapMock = Mockito.mock(Map.class);
        Iterator<List<ExtensionElement>> collectionIteratorMock = Mockito.mock(Iterator.class);
        List<ExtensionElement> messageExtensionElementListMock = Mockito.mock(List.class);
        Iterator<ExtensionElement> extensionElementIteratorMock = Mockito.mock(Iterator.class);
        ExtensionElement extensionElementMock = Mockito.mock(ExtensionElement.class);

        Mockito.when(bpmnParseMock.getBpmnModel()).thenReturn(bpmnModelMock);
        Mockito.when(messageEventDefinitionMock.getMessageRef()).thenReturn("messageId");
        Mockito.when(bpmnModelMock.containsMessageId("messageId")).thenReturn(true);
        Mockito.when(bpmnModelMock.getMessage("messageId")).thenReturn(messageMock);
        Mockito.when(messageMock.getName()).thenReturn("MessageWithExtensionElements");
        Mockito.when(messageMock.getExtensionElements()).thenReturn(messageExtensionElementMapMock);
        Mockito.when(messageExtensionElementMapMock.values()).thenReturn(messageExtensionElementListCollectionMock);

        Mockito.when(messageExtensionElementListCollectionMock.iterator()).thenReturn(collectionIteratorMock);
        Mockito.when(collectionIteratorMock.hasNext()).thenReturn(true, false);
        Mockito.when(collectionIteratorMock.next()).thenReturn(messageExtensionElementListMock);
        Mockito.when(collectionIteratorMock.next()).thenReturn(messageExtensionElementListMock);
        Mockito.when(messageExtensionElementListMock.iterator()).thenReturn(extensionElementIteratorMock);
        Mockito.when(extensionElementIteratorMock.hasNext()).thenReturn(true,false);
        Mockito.when(extensionElementIteratorMock.next()).thenReturn(extensionElementMock);

        MessageEventDefinitionParseHandler handler = new MessageEventDefinitionParseHandler();
        handler.parse(bpmnParseMock, messageEventDefinitionMock);

        Mockito.verify(messageEventDefinitionMock).setMessageRef("MessageWithExtensionElements");
        Mockito.verify(messageEventDefinitionMock).addExtensionElement(extensionElementMock);
    }
}