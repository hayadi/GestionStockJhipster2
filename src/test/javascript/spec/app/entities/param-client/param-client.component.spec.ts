import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { ParamClientComponent } from 'app/entities/param-client/param-client.component';
import { ParamClientService } from 'app/entities/param-client/param-client.service';
import { ParamClient } from 'app/shared/model/param-client.model';

describe('Component Tests', () => {
  describe('ParamClient Management Component', () => {
    let comp: ParamClientComponent;
    let fixture: ComponentFixture<ParamClientComponent>;
    let service: ParamClientService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [ParamClientComponent],
      })
        .overrideTemplate(ParamClientComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ParamClientComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ParamClientService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ParamClient(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.paramClients && comp.paramClients[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
