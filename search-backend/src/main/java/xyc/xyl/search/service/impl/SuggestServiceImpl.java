package xyc.xyl.search.service.impl;


import xyc.xyl.search.model.dto.post.PostEsDTO;
import xyc.xyl.search.service.SuggestService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chunyang.leng
 * @date 2023-08-18 18:29
 */
@Service
@Slf4j
public class SuggestServiceImpl implements SuggestService {
    /**
     * 搜索建议 key
     */
    private static final String SUGGEST_TAG = "suggest_query";

//    @Resource
//    private ElasticsearchClient elasticsearchClient;
    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    /**
     * 根据 关键词，返回搜索建议
     *
     * @param pre 搜索关键词
     * @return 搜索建议，10条
     */
    @Override
    public List<String> suggest(String pre) {
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion("my_suggest",
                SuggestBuilders.completionSuggestion("title_suggest")
                        .prefix(pre)
                        .size(10));
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.fetchSource(new String[] {"title"}, null);
        log.info(String.valueOf(suggestBuilder));
        SearchResponse suggest = elasticsearchRestTemplate.suggest(suggestBuilder, PostEsDTO.class);
        Suggest.Suggestion suggestion = suggest.getSuggest().getSuggestion("my_suggest");
        List<String> returnList = new ArrayList<>();
        for(Object entry : suggestion.getEntries()) {
            CompletionSuggestion.Entry item = (CompletionSuggestion.Entry) entry;
            List<CompletionSuggestion.Entry.Option> options = item.getOptions();
            if(!CollectionUtils.isEmpty(options)) {
                for (Suggest.Suggestion.Entry.Option option : options) {
                    String op = option.getText().toString();
                    if (!returnList.contains(op)) {
                        returnList.add(option.getText().toString());
                    }
                }
            }
        }
        return returnList;
//        SearchRequest completionSuggestSearchRequest = new SearchRequest
//                .Builder()
//                .suggest(
//                        new Suggester
//                                .Builder()
//                                .suggesters(SUGGEST_TAG, builder -> builder.prefix(match)
//                                                .completion(new CompletionSuggester
//                                                        .Builder()
//                                                        .field("title_suggest")
//                                                        .size(10)
//                                                        .build()
//                                        )
//                                )
//                                .build())
//                .build();
//
//        SearchResponse<PostEsDTO> completionSuggestSearch = elasticsearchClient.search(completionSuggestSearchRequest, PostEsDTO.class);
//
//        Map<String, List<Suggestion<PostEsDTO>>> suggest = completionSuggestSearch.suggest();
//
//        List<Suggestion<PostEsDTO>> suggestions = suggest.get(SUGGEST_TAG);
//
//        return suggestions
//                .parallelStream()
//                .flatMap(x -> x.completion()
//                        .options()
//                        .stream()
//                        .map(CompletionSuggestOption::text))
//                .collect(Collectors.toList());

    }
}
