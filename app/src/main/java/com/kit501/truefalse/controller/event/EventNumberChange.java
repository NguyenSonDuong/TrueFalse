package com.kit501.truefalse.controller.event;

import com.kit501.truefalse.model.KeySaveModel;

public interface EventNumberChange {
    void OnChange(KeySaveModel key, int number);
}
