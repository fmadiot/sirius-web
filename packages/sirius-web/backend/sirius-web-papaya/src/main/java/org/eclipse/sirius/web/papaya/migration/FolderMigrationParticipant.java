/*******************************************************************************
 * Copyright (c) 2025 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.web.papaya.migration;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.components.emf.migration.api.IMigrationParticipant;
import org.eclipse.sirius.components.papaya.PapayaPackage;
import org.springframework.stereotype.Service;

/**
 * Migration participant that helps with the support for folders.
 *
 * @author sbegaudeau
 */
@Service
public class FolderMigrationParticipant implements IMigrationParticipant {

    @Override
    public String getVersion() {
        return "2025.06.0-202504251632";
    }

    @Override
    public EStructuralFeature getEStructuralFeature(EClass eClass, String eStructuralFeatureName) {
        var featureNamesToUpdate = List.of(
                "components",
                "componentExchanges",
                "iterations",
                "tasks",
                "contributions",
                "applicationConcerns",
                "domains",
                "channels"
        );
        if (eClass.getName().equals("Project") && featureNamesToUpdate.contains(eStructuralFeatureName)) {
            return PapayaPackage.eINSTANCE.getContainer_Elements();
        }
        return IMigrationParticipant.super.getEStructuralFeature(eClass, eStructuralFeatureName);
    }
}
