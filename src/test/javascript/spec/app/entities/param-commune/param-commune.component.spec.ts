import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { ParamCommuneComponent } from 'app/entities/param-commune/param-commune.component';
import { ParamCommuneService } from 'app/entities/param-commune/param-commune.service';
import { ParamCommune } from 'app/shared/model/param-commune.model';

describe('Component Tests', () => {
  describe('ParamCommune Management Component', () => {
    let comp: ParamCommuneComponent;
    let fixture: ComponentFixture<ParamCommuneComponent>;
    let service: ParamCommuneService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [ParamCommuneComponent],
      })
        .overrideTemplate(ParamCommuneComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ParamCommuneComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ParamCommuneService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ParamCommune(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.paramCommunes && comp.paramCommunes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
