package xyc.xyl.search.service;

import java.io.IOException;
import java.util.List;

public  interface SuggestService {
    List<String> suggest(String match) throws IOException;
}
