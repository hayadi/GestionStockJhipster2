import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { AdminPrivilegeProfilDetailComponent } from 'app/entities/admin-privilege-profil/admin-privilege-profil-detail.component';
import { AdminPrivilegeProfil } from 'app/shared/model/admin-privilege-profil.model';

describe('Component Tests', () => {
  describe('AdminPrivilegeProfil Management Detail Component', () => {
    let comp: AdminPrivilegeProfilDetailComponent;
    let fixture: ComponentFixture<AdminPrivilegeProfilDetailComponent>;
    const route = ({ data: of({ adminPrivilegeProfil: new AdminPrivilegeProfil(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [AdminPrivilegeProfilDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AdminPrivilegeProfilDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AdminPrivilegeProfilDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load adminPrivilegeProfil on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.adminPrivilegeProfil).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
