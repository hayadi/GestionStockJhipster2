package dz.a3bc.stock.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import dz.a3bc.stock.web.rest.TestUtil;

public class ParamArticleTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParamArticle.class);
        ParamArticle paramArticle1 = new ParamArticle();
        paramArticle1.setId(1L);
        ParamArticle paramArticle2 = new ParamArticle();
        paramArticle2.setId(paramArticle1.getId());
        assertThat(paramArticle1).isEqualTo(paramArticle2);
        paramArticle2.setId(2L);
        assertThat(paramArticle1).isNotEqualTo(paramArticle2);
        paramArticle1.setId(null);
        assertThat(paramArticle1).isNotEqualTo(paramArticle2);
    }
}
