import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { ParamWilayaComponent } from 'app/entities/param-wilaya/param-wilaya.component';
import { ParamWilayaService } from 'app/entities/param-wilaya/param-wilaya.service';
import { ParamWilaya } from 'app/shared/model/param-wilaya.model';

describe('Component Tests', () => {
  describe('ParamWilaya Management Component', () => {
    let comp: ParamWilayaComponent;
    let fixture: ComponentFixture<ParamWilayaComponent>;
    let service: ParamWilayaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [ParamWilayaComponent],
      })
        .overrideTemplate(ParamWilayaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ParamWilayaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ParamWilayaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ParamWilaya(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.paramWilayas && comp.paramWilayas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
