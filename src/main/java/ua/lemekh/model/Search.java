package ua.lemekh.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Created by Ostap on 19.06.2017.
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Search {
    private String searchRow;

    public String getSearchRow() {
        return searchRow;
    }

    public void setSearchRow(String searchRow) {
        this.searchRow = searchRow;
    }
}
