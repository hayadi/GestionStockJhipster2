import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'param-unite-mesure',
        loadChildren: () =>
          import('./param-unite-mesure/param-unite-mesure.module').then(m => m.GestionStockJhipsterParamUniteMesureModule),
      },
      {
        path: 'param-wilaya',
        loadChildren: () => import('./param-wilaya/param-wilaya.module').then(m => m.GestionStockJhipsterParamWilayaModule),
      },
      {
        path: 'param-commune',
        loadChildren: () => import('./param-commune/param-commune.module').then(m => m.GestionStockJhipsterParamCommuneModule),
      },
      {
        path: 'param-famille-article',
        loadChildren: () =>
          import('./param-famille-article/param-famille-article.module').then(m => m.GestionStockJhipsterParamFamilleArticleModule),
      },
      {
        path: 'param-article',
        loadChildren: () => import('./param-article/param-article.module').then(m => m.GestionStockJhipsterParamArticleModule),
      },
      {
        path: 'param-fournisseur',
        loadChildren: () => import('./param-fournisseur/param-fournisseur.module').then(m => m.GestionStockJhipsterParamFournisseurModule),
      },
      {
        path: 'param-client',
        loadChildren: () => import('./param-client/param-client.module').then(m => m.GestionStockJhipsterParamClientModule),
      },
      {
        path: 'param-model-article',
        loadChildren: () =>
          import('./param-model-article/param-model-article.module').then(m => m.GestionStockJhipsterParamModelArticleModule),
      },
      {
        path: 'admin-employe',
        loadChildren: () => import('./admin-employe/admin-employe.module').then(m => m.GestionStockJhipsterAdminEmployeModule),
      },
      {
        path: 'admin-privilege',
        loadChildren: () => import('./admin-privilege/admin-privilege.module').then(m => m.GestionStockJhipsterAdminPrivilegeModule),
      },
      {
        path: 'admin-profil',
        loadChildren: () => import('./admin-profil/admin-profil.module').then(m => m.GestionStockJhipsterAdminProfilModule),
      },
      {
        path: 'admin-privilege-profil',
        loadChildren: () =>
          import('./admin-privilege-profil/admin-privilege-profil.module').then(m => m.GestionStockJhipsterAdminPrivilegeProfilModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class GestionStockJhipsterEntityModule {}
