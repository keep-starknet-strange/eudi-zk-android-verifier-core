/*
 * Copyright (c) 2025 European Commission
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.europa.ec.eudi.verifier.core.request

import eu.europa.ec.eudi.verifier.core.DataElementIdentifier
import eu.europa.ec.eudi.verifier.core.DocType
import eu.europa.ec.eudi.verifier.core.Namespace
import org.multipaz.mdoc.zkp.ZkSystemSpec
import java.security.cert.X509Certificate

/**
 * Represents a generic request in the verifier core system.
 * Used as a marker interface for different types of requests.
 */
interface Request

/**
 * Represents a request sent to a device, containing a list of document requests.
 *
 * @param docRequests The list of document requests to be processed by the device.
 */
class DeviceRequest(
    val docRequests: List<DocRequest>
) : Request

/**
 * Represents a request for a specific document type, including requested items and reader authentication.
 *
 * @property docType The type of document being requested.
 * @property itemsRequest The map of namespaces to data element identifiers and their intent to retain.
 * @property readerAuthCertificate The certificate used for reader authentication, if available.
 * @property zkSystemSpecs The Zero-Knowledge system specs the verifier accepts for this document. When
 * non-empty, a `ZkRequest` is attached to the document request and a ZK-capable wallet may respond with
 * a zero-knowledge proof instead of the plaintext document. Empty (default) means no ZK is requested.
 */
data class DocRequest(
    val docType: DocType,
    var itemsRequest: ItemsRequest,
    var readerAuthCertificate: X509Certificate?,
    var zkSystemSpecs: List<ZkSystemSpec> = emptyList()
)

/**
 * Type alias for the items request map structure.
 */
typealias ItemsRequest = Map<Namespace, Map<DataElementIdentifier, IntentToRetain>>

/**
 * Type alias for the intent to retain boolean value.
 */
typealias IntentToRetain = Boolean
