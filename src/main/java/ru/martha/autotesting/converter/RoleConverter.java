package ru.martha.autotesting.converter;

import ru.martha.autotesting.entity.Role;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.util.List;
import java.util.Optional;

public class RoleConverter implements Converter {
    private final List<Role> roles;

    public RoleConverter(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Optional<Role> roleOptional = roles.stream().filter(r -> s.equals(r.getName())).findAny();
        return roleOptional.orElse(null);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return ((Role) o).getName();
    }
}
