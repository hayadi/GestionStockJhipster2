import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { ParamWilayaDetailComponent } from 'app/entities/param-wilaya/param-wilaya-detail.component';
import { ParamWilaya } from 'app/shared/model/param-wilaya.model';

describe('Component Tests', () => {
  describe('ParamWilaya Management Detail Component', () => {
    let comp: ParamWilayaDetailComponent;
    let fixture: ComponentFixture<ParamWilayaDetailComponent>;
    const route = ({ data: of({ paramWilaya: new ParamWilaya(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [ParamWilayaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ParamWilayaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ParamWilayaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load paramWilaya on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.paramWilaya).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
