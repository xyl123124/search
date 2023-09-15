package xyc.xyl.search;

import xyc.xyl.search.esdao.PostEsDao;
import xyc.xyl.search.model.dto.post.PostEsDTO;
import xyc.xyl.search.model.entity.Post;
import xyc.xyl.search.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
class SearchApplicationTests {
    @Resource
    private PostService postService;

    @Resource
    private PostEsDao postEsDao;
    @Test
    void contextLoads() {


        List<Post> postList = postService.list();
        if (CollectionUtils.isEmpty(postList)) {
            return;
        }
        List<PostEsDTO> postEsDTOList = postList.stream()
                .map(PostEsDTO::objToDto)
                .collect(Collectors.toList());
        final int pageSize = 500;
        int total = postEsDTOList.size();
        log.info("FullSyncPostToEs start, total {}", total);
        for (int i = 0; i < total; i += pageSize) {
            int end = Math.min(i + pageSize, total);
            log.info("sync from {} to {}", i, end);
            postEsDao.saveAll(postEsDTOList.subList(i, end));
        }
        log.info("FullSyncPostToEs end, total {}", total);
        log.info(postEsDTOList.stream().map(PostEsDTO::getTitle_suggest).collect(Collectors.toList()).toString());
    }

}
