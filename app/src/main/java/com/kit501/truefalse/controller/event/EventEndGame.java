package com.kit501.truefalse.controller.event;

import com.kit501.truefalse.model.ScoresModel;

public interface EventEndGame {
    void onEndGame(ScoresModel scoresModel);
}
