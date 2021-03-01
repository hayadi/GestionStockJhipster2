package dz.a3bc.stock.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import dz.a3bc.stock.web.rest.TestUtil;

public class ParamModelArticleTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParamModelArticle.class);
        ParamModelArticle paramModelArticle1 = new ParamModelArticle();
        paramModelArticle1.setId(1L);
        ParamModelArticle paramModelArticle2 = new ParamModelArticle();
        paramModelArticle2.setId(paramModelArticle1.getId());
        assertThat(paramModelArticle1).isEqualTo(paramModelArticle2);
        paramModelArticle2.setId(2L);
        assertThat(paramModelArticle1).isNotEqualTo(paramModelArticle2);
        paramModelArticle1.setId(null);
        assertThat(paramModelArticle1).isNotEqualTo(paramModelArticle2);
    }
}
