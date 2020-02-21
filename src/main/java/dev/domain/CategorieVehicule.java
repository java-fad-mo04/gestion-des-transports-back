package dev.domain;

/**
 * Représente le concept de catégorie de véhicule qui correspond au segment
 * automobile européen
 * 
 * @author DIGINAMIC
 *
 */
public enum CategorieVehicule {
	MICROURBAINES("Micro-urbaine"), MINICITADINES("Mini-citadines"), CITADINES_POLYVALENTES(
			"Citadines polyvalentes"), COMPACTES("Compactes"), BERLINES_S("Berlines Taille S"), BERLINES_M(
					"Berlines Taille M"), BERLINES_L("Berlines Taille L"), SUV_ETC("SUV, Tout-terrains et Pick-up");

	private final String description;

	private CategorieVehicule(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

}
