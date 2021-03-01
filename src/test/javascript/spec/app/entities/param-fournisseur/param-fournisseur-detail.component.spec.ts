import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { ParamFournisseurDetailComponent } from 'app/entities/param-fournisseur/param-fournisseur-detail.component';
import { ParamFournisseur } from 'app/shared/model/param-fournisseur.model';

describe('Component Tests', () => {
  describe('ParamFournisseur Management Detail Component', () => {
    let comp: ParamFournisseurDetailComponent;
    let fixture: ComponentFixture<ParamFournisseurDetailComponent>;
    const route = ({ data: of({ paramFournisseur: new ParamFournisseur(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [ParamFournisseurDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ParamFournisseurDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ParamFournisseurDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load paramFournisseur on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.paramFournisseur).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
