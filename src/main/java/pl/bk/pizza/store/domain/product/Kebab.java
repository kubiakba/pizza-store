package pl.bk.pizza.store.domain.product;

import pl.bk.pizza.store.domain.exception.InvalidEntityException;

import static org.apache.commons.lang.StringUtils.isBlank;
import static pl.bk.pizza.store.domain.exception.ErrorCode.*;
import static pl.bk.pizza.store.domain.exception.Preconditions.check;

public class Kebab implements ProductInfo{

    private final String name;
    private final String description;

    public Kebab(String description, String name) {
        validateDescription(description);
        validateName(name);
        this.name = name;
        this.description = description;
    }

    private void validateName(String name) {
        check(isBlank(name),() -> new InvalidEntityException("Name is null or empty.",
            EMPTY_KEBAB_NAME));
    }

    private void validateDescription(String description) {
        check(isBlank(description),() -> new InvalidEntityException("Description is null or empty.",
            EMPTY_KEBAB_DESCRIPTION));
    }

    public String getName() { return name; }

    public String getDescription(){
        return description;
    }

}
