import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { AdminPrivilegeDetailComponent } from 'app/entities/admin-privilege/admin-privilege-detail.component';
import { AdminPrivilege } from 'app/shared/model/admin-privilege.model';

describe('Component Tests', () => {
  describe('AdminPrivilege Management Detail Component', () => {
    let comp: AdminPrivilegeDetailComponent;
    let fixture: ComponentFixture<AdminPrivilegeDetailComponent>;
    const route = ({ data: of({ adminPrivilege: new AdminPrivilege(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [AdminPrivilegeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AdminPrivilegeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AdminPrivilegeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load adminPrivilege on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.adminPrivilege).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
