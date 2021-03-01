package dz.a3bc.stock;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("dz.a3bc.stock");

        noClasses()
            .that()
            .resideInAnyPackage("dz.a3bc.stock.service..")
            .or()
            .resideInAnyPackage("dz.a3bc.stock.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..dz.a3bc.stock.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
