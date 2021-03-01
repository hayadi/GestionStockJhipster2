package dz.a3bc.stock.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import dz.a3bc.stock.web.rest.TestUtil;

public class ParamClientTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParamClient.class);
        ParamClient paramClient1 = new ParamClient();
        paramClient1.setId(1L);
        ParamClient paramClient2 = new ParamClient();
        paramClient2.setId(paramClient1.getId());
        assertThat(paramClient1).isEqualTo(paramClient2);
        paramClient2.setId(2L);
        assertThat(paramClient1).isNotEqualTo(paramClient2);
        paramClient1.setId(null);
        assertThat(paramClient1).isNotEqualTo(paramClient2);
    }
}
