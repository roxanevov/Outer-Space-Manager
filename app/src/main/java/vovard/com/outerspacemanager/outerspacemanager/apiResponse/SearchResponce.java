package vovard.com.outerspacemanager.outerspacemanager.apiResponse;

import java.util.List;

import vovard.com.outerspacemanager.outerspacemanager.entity.Search;

public class SearchResponce {
    private Integer size;

    private List<Search> searches;

    public List<Search> getSearches() {
        return searches;
    }

}
