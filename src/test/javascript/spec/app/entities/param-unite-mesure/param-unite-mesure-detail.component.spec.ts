import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { ParamUniteMesureDetailComponent } from 'app/entities/param-unite-mesure/param-unite-mesure-detail.component';
import { ParamUniteMesure } from 'app/shared/model/param-unite-mesure.model';

describe('Component Tests', () => {
  describe('ParamUniteMesure Management Detail Component', () => {
    let comp: ParamUniteMesureDetailComponent;
    let fixture: ComponentFixture<ParamUniteMesureDetailComponent>;
    const route = ({ data: of({ paramUniteMesure: new ParamUniteMesure(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [ParamUniteMesureDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ParamUniteMesureDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ParamUniteMesureDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load paramUniteMesure on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.paramUniteMesure).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
