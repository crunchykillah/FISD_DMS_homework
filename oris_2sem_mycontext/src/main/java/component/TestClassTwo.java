package component;

import annotation.Component;
import annotation.Inject;
import lombok.Getter;

@Component
@Getter
public class TestClassTwo {

    @Inject
    public TestClassOne testClassOne;


}
