package com.config.pad.content.libding.rerxmvp.interfaceUtils;

import com.config.pad.content.libding.rerxmvp.base.AWanBasePresenter;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Function: 标注创建presenter的注解
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenter {
    Class< ? extends AWanBasePresenter> value();
}
