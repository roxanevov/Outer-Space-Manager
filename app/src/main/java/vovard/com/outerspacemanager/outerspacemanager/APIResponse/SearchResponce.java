package vovard.com.outerspacemanager.outerspacemanager.APIResponse;

import java.util.List;

import vovard.com.outerspacemanager.outerspacemanager.Entity.Search;

public class SearchResponce {
    private Integer size;

    private List<Search> searches;

    public List<Search> getSearches() {
        return searches;
    }

}
