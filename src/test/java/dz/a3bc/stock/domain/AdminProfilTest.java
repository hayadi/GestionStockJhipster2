package dz.a3bc.stock.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import dz.a3bc.stock.web.rest.TestUtil;

public class AdminProfilTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdminProfil.class);
        AdminProfil adminProfil1 = new AdminProfil();
        adminProfil1.setId(1L);
        AdminProfil adminProfil2 = new AdminProfil();
        adminProfil2.setId(adminProfil1.getId());
        assertThat(adminProfil1).isEqualTo(adminProfil2);
        adminProfil2.setId(2L);
        assertThat(adminProfil1).isNotEqualTo(adminProfil2);
        adminProfil1.setId(null);
        assertThat(adminProfil1).isNotEqualTo(adminProfil2);
    }
}
