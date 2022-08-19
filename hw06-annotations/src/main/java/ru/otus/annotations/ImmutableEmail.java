package ru.otus.annotations;

import ru.otus.annotations.Email;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
@Email
@Immutable
@Inherited
public @interface ImmutableEmail {
}
