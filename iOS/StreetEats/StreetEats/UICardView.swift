//
// Created by Versilis Tyson on 3/11/20.
// Copyright (c) 2020 Versilis. All rights reserved.
//

import SwiftUI
import MaterialComponents.MDCCard

struct UICardView: UIViewRepresentable {
    func makeUIView(context: Context) -> MDCCard {
        let cardView = MDCCard()
        cardView.cornerRadius = 12
        return cardView
    }

    func updateUIView(_ uiView: MDCCard, context: Context) {
    }
}