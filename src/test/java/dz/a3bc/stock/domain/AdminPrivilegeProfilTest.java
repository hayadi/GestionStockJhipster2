package dz.a3bc.stock.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import dz.a3bc.stock.web.rest.TestUtil;

public class AdminPrivilegeProfilTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdminPrivilegeProfil.class);
        AdminPrivilegeProfil adminPrivilegeProfil1 = new AdminPrivilegeProfil();
        adminPrivilegeProfil1.setId(1L);
        AdminPrivilegeProfil adminPrivilegeProfil2 = new AdminPrivilegeProfil();
        adminPrivilegeProfil2.setId(adminPrivilegeProfil1.getId());
        assertThat(adminPrivilegeProfil1).isEqualTo(adminPrivilegeProfil2);
        adminPrivilegeProfil2.setId(2L);
        assertThat(adminPrivilegeProfil1).isNotEqualTo(adminPrivilegeProfil2);
        adminPrivilegeProfil1.setId(null);
        assertThat(adminPrivilegeProfil1).isNotEqualTo(adminPrivilegeProfil2);
    }
}
