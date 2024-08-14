package bg.softuni.usermanagementapp.model.annotation;

import bg.softuni.usermanagementapp.util.UUIDSequenceGenerator;
import org.hibernate.annotations.IdGeneratorType;
import org.hibernate.annotations.ValueGenerationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@IdGeneratorType(UUIDSequenceGenerator.class)
@ValueGenerationType(generatedBy = UUIDSequenceGenerator.class)
public @interface UUIDSequence {
}
