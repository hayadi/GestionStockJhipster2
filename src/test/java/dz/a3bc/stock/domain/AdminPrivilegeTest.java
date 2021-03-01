package dz.a3bc.stock.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import dz.a3bc.stock.web.rest.TestUtil;

public class AdminPrivilegeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdminPrivilege.class);
        AdminPrivilege adminPrivilege1 = new AdminPrivilege();
        adminPrivilege1.setId(1L);
        AdminPrivilege adminPrivilege2 = new AdminPrivilege();
        adminPrivilege2.setId(adminPrivilege1.getId());
        assertThat(adminPrivilege1).isEqualTo(adminPrivilege2);
        adminPrivilege2.setId(2L);
        assertThat(adminPrivilege1).isNotEqualTo(adminPrivilege2);
        adminPrivilege1.setId(null);
        assertThat(adminPrivilege1).isNotEqualTo(adminPrivilege2);
    }
}
