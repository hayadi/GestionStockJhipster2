import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { ParamFournisseurComponent } from 'app/entities/param-fournisseur/param-fournisseur.component';
import { ParamFournisseurService } from 'app/entities/param-fournisseur/param-fournisseur.service';
import { ParamFournisseur } from 'app/shared/model/param-fournisseur.model';

describe('Component Tests', () => {
  describe('ParamFournisseur Management Component', () => {
    let comp: ParamFournisseurComponent;
    let fixture: ComponentFixture<ParamFournisseurComponent>;
    let service: ParamFournisseurService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [ParamFournisseurComponent],
      })
        .overrideTemplate(ParamFournisseurComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ParamFournisseurComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ParamFournisseurService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ParamFournisseur(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.paramFournisseurs && comp.paramFournisseurs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
