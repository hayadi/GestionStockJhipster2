import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { ParamUniteMesureComponent } from 'app/entities/param-unite-mesure/param-unite-mesure.component';
import { ParamUniteMesureService } from 'app/entities/param-unite-mesure/param-unite-mesure.service';
import { ParamUniteMesure } from 'app/shared/model/param-unite-mesure.model';

describe('Component Tests', () => {
  describe('ParamUniteMesure Management Component', () => {
    let comp: ParamUniteMesureComponent;
    let fixture: ComponentFixture<ParamUniteMesureComponent>;
    let service: ParamUniteMesureService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [ParamUniteMesureComponent],
      })
        .overrideTemplate(ParamUniteMesureComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ParamUniteMesureComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ParamUniteMesureService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ParamUniteMesure(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.paramUniteMesures && comp.paramUniteMesures[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
