package com.versilistyson.androidstreeteats.data.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.versilistyson.androidstreeteats.data.firebase.db.FirestoreService
import com.versilistyson.androidstreeteats.data.util.setDocumentAndMerge
import com.versilistyson.androidstreeteats.data.util.updateDocument

abstract class FirestoreDataSource(private val firestoreService: FirestoreService, baseCollectionName: String = "") {

    protected open val baseCollectionReference by lazy {
        firestoreService.instance.collection(baseCollectionName)
    }

    protected open fun writeDocumentAndMerge(
        collectionRef: CollectionReference,
        documentName: String,
        documentFields: HashMap<String, Any?>
    ): Task<Void> =
        collectionRef.document(documentName).setDocumentAndMerge(documentFields)

    protected open fun updateDocument(
        collectionRef: CollectionReference,
        docName: String,
        updatedDocumentFields: HashMap<String, Any?>
    ): Task<Void> =
        collectionRef.document(docName).updateDocument(updatedDocumentFields)

    protected open fun fetchDocumentSnapshot(
        collectionRef: CollectionReference,
        documentName: String
    ) : Task<DocumentSnapshot> =
        collectionRef.document(documentName).get()
}