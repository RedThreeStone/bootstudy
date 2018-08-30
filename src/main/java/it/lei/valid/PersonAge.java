package it.lei.valid;

import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonAge {
    int maxAge() default 100;
    int minAge() default 0;
    String message() default "人的年龄必须要在{maxAge}和{minAge}之间";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
