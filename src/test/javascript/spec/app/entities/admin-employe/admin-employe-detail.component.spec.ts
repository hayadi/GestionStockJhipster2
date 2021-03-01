import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { AdminEmployeDetailComponent } from 'app/entities/admin-employe/admin-employe-detail.component';
import { AdminEmploye } from 'app/shared/model/admin-employe.model';

describe('Component Tests', () => {
  describe('AdminEmploye Management Detail Component', () => {
    let comp: AdminEmployeDetailComponent;
    let fixture: ComponentFixture<AdminEmployeDetailComponent>;
    const route = ({ data: of({ adminEmploye: new AdminEmploye(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [AdminEmployeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AdminEmployeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AdminEmployeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load adminEmploye on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.adminEmploye).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
