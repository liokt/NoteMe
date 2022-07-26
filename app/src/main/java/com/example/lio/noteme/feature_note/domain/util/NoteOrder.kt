package com.example.lio.noteme.feature_note.domain.util

import com.example.lio.noteme.feature_note.domain.model.Note

sealed class NoteOrder(val orderType: OrderType) {
    class Title(orderType: OrderType): NoteOrder(orderType)
    class Date(orderType: OrderType): NoteOrder(orderType)
    class Color(orderType: OrderType): NoteOrder(orderType)
}
