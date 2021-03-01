import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { AdminProfilDetailComponent } from 'app/entities/admin-profil/admin-profil-detail.component';
import { AdminProfil } from 'app/shared/model/admin-profil.model';

describe('Component Tests', () => {
  describe('AdminProfil Management Detail Component', () => {
    let comp: AdminProfilDetailComponent;
    let fixture: ComponentFixture<AdminProfilDetailComponent>;
    const route = ({ data: of({ adminProfil: new AdminProfil(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [AdminProfilDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AdminProfilDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AdminProfilDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load adminProfil on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.adminProfil).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
