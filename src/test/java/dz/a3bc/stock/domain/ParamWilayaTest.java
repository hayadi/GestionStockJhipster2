package dz.a3bc.stock.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import dz.a3bc.stock.web.rest.TestUtil;

public class ParamWilayaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParamWilaya.class);
        ParamWilaya paramWilaya1 = new ParamWilaya();
        paramWilaya1.setId(1L);
        ParamWilaya paramWilaya2 = new ParamWilaya();
        paramWilaya2.setId(paramWilaya1.getId());
        assertThat(paramWilaya1).isEqualTo(paramWilaya2);
        paramWilaya2.setId(2L);
        assertThat(paramWilaya1).isNotEqualTo(paramWilaya2);
        paramWilaya1.setId(null);
        assertThat(paramWilaya1).isNotEqualTo(paramWilaya2);
    }
}
